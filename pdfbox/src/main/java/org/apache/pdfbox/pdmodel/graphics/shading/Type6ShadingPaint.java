/*
 * Copyright 2014 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.util.Matrix;

/**
 * AWT Paint for coons patch meshes (Type 6) shading. This was done as part of
 * GSoC2014, Tilman Hausherr is the mentor.
 *
 * @author Shaola Ren
 */
class Type6ShadingPaint implements Paint
{
    private static final Log LOG = LogFactory.getLog(Type6ShadingPaint.class);

    private final PDShadingType6 shading;
    private final Matrix ctm;

    /**
     * Constructor.
     *
     * @param shading the shading resources
     * @param ctm current transformation matrix
     */
    public Type6ShadingPaint(PDShadingType6 shading, Matrix ctm)
    {
        this.shading = shading;
        this.ctm = ctm;
    }

    @Override
    public int getTransparency()
    {
        return 0;
    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds,
            AffineTransform xform, RenderingHints hints)
    {
        try
        {
            return new Type6ShadingContext(shading, cm, xform, ctm, deviceBounds);
        }
        catch (IOException ex)
        {
            LOG.error(ex);
            return null;
        }
    }
}
